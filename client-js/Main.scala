import scala.scalajs.js
import js.Dynamic.{global => g, newInstance => jsnew}

import com.example.protos.hello._
import scala.concurrent.ExecutionContext.Implicits.global

object HelloWorldClientSjs extends App {

  val proto = grpc.load("../server/src/main/protobuf/hello.proto").com.example.protos

  val client = jsnew(proto.Greeter)("localhost:5050", grpc.credentials.createInsecure()).asInstanceOf[js.Dynamic]

  GreeterGrpc
    .stub(client)
    .sayHello(HelloRequest(name = "World"))
    .onComplete{
      case scala.util.Success(r) => println(r.message)
      case _ => println("err")
    }
}

import scala.scalajs.js.annotation.JSImport

@JSImport("grpc", JSImport.Namespace)
@js.native
object grpc extends js.Object {
  def load(path: String): js.Dynamic = js.native
  val credentials: js.Dynamic = js.native
}
