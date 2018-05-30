import io.grpc.ManagedChannelBuilder
import com.example.protos.hello._

import scala.concurrent.ExecutionContext.Implicits.global

object HelloWorldClient extends App {

  val channel = ManagedChannelBuilder
    .forAddress("127.0.0.1", 5050)
    .usePlaintext(true)
    .build

  GreeterGrpc
    .stub(channel)
    .sayHello(HelloRequest(name = "World"))
    .onComplete(println)

}