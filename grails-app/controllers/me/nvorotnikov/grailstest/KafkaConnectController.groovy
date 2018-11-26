package me.nvorotnikov.grailstest

import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

class KafkaConnectController {

    def kafkaConnectService

    def index() {
        exportLogToTxt()
        [messages:KafkaConnect.list()]
    }

    def retrieveLogMessages() {
        [messages:KafkaConnect.list()]
    }

    def exportLogToTxt() {
        File file = new File("/home/niko/Downloads/LogFile.txt")

        def lst = KafkaConnect.list().logMessage
        lst.each {
            file << "${it}\n"
        }
    }

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
            }
            consumer.commitAsync()
        }
        consumer.close()
        println("DONE")
    }

//    def stopConsumer() {
//        if(consumer != null)
//            println("nnot null")
//
//        consumer.close()
//        println("CLOSE")
//    }

}
