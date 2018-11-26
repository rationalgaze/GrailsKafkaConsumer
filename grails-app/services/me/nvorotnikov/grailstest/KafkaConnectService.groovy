package me.nvorotnikov.grailstest

import grails.transaction.Transactional

@Transactional
class KafkaConnectService {

    @Transactional
    KafkaConnect create(String m) {
        return new KafkaConnect(logMessage: m).save()
    }

}
