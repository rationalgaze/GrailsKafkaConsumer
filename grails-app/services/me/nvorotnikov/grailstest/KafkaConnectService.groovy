package me.nvorotnikov.grailstest

import grails.transaction.Transactional

@Transactional
class KafkaConnectService {

    private KafkaConnect kc

    @Transactional
    KafkaConnect create(String m) {
        KafkaConnect kc = new KafkaConnect(logMessage: m)
        kc.save()
        return kc
    }

    @Transactional(readOnly = true)
    def listKafkaConnect(){

        println(KafkaConnect.list())
        KafkaConnect.list()
    }
}
