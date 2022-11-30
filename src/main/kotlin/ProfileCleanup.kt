import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import org.litote.kmongo.KMongo
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection
import org.litote.kmongo.util.KMongoUtil

data class Profile(val _id: String, val brandId: String)

var mongoClientURI = "mongodb+srv://usr:pass@prod01.boujg.mongodb.net/profile-db"

val client =
    KMongo.createClient(MongoClientSettings
        .builder()
        .codecRegistry(KMongoUtil.defaultCodecRegistry)
        .applyToSslSettings {
            it.enabled(true)
        }
        .applyConnectionString(ConnectionString(mongoClientURI))
        .build()) //get com.mongodb.MongoClient new instance
val database = client.getDatabase("profile-db") //normal java driver usage
val col = database.getCollection<Profile>()


fun main() {
    val producer = createProducer()
    col.find(Profile::brandId eq "vetoro")
        .forEach {
            val message =
                """{"@type":"DeletePlayer","name":"DeletePlayer","playerUUID":"${it._id}","brandId":"fxnobels","e2e":false,"force":true}
"""
            println(
                message
            )
//            val future = producer.send(ProducerRecord("e2e_cleanup", Random.nextInt().toString(), message))
//            future.get()
        }
}