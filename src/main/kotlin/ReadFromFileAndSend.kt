import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.common.serialization.StringSerializer
import java.io.File
import java.util.Properties

fun createProducer(): Producer<String, String> {
    val props = Properties()
    props["bootstrap.servers"] = "localhost:9092"
    props["key.serializer"] = StringSerializer::class.java
    props["value.serializer"] = StringSerializer::class.java
    return KafkaProducer(props)
}

fun main() {
    val producer = createProducer()

    File("/Users/vadymyemelianov/IdeaProjects/clenup/src/main/resources/players.txt")
        .forEachLine {
            println(
                """{"@type":"DeletePlayer","name":"DeletePlayer","playerUUID":"$it","brandId":"fxnobels","e2e":false,"force":true}
"""
            )
//            val future = producer.send(ProducerRecord("Topic1", i.toString(), "Hello world $i"))
//            future.get()
        }

}