package me.nvorotnikov.grailstest

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

class KafkaConnectController {

    def kafkaConnectService

    /**
     * Render index page
     */
    def index() {
    }

    def retrieveLogMessages() {
        [messages:KafkaConnect.list()]
    }

    /**
     * Export log to TXT file
     */
    def exportLogToTxt() {
        File file = null
        try {
            file = new File("/home/niko/Downloads/LogFile.txt")

            def lst = KafkaConnect.list().logMessage
            lst.each {
                file << "${it}\n"
            }
            flash.success = "Log successfully saved in '/home/niko/Downloads/LogFile.txt'"
        } catch (IOException e) {
            flash.error = "Ooops, something going wrong ! \n ${e}"
        }
        redirect(action: "index")
    }

    /**
     * Adding lines to TXT file
     */
    def addLineToTxt(String line) {
        File file = new File("/home/niko/Downloads/LogFile2.txt")

        file << "${line}\n"
        flash.success = "Line added in LogFile2.txt"

    }


    /**
     * Set Kafka Consumer Properties
     * @return java.util.Properties.
     */
    def setProperties() {
        Properties props = new Properties()
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer")
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName())
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName())
        return props
    }

    /**
     * Start Consuming data from kafka topic
     */
    def runConsumer() {

        Properties props = setProperties()

        // Create the consumer using properties.
        Consumer<String, String> consumer = new KafkaConsumer<>(props)
        consumer.subscribe(["connect-test"])

        println("Started")
        while(true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(1000)

            consumerRecords.each{
                record ->
                    String v = record.value().toString()
                    println v
                    kafkaConnectService.create(v)
                    addLineToTxt(v)
            }
            consumer.commitAsync()
        }
        consumer.close()
        println("DONE")
    }

}
