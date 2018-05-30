import scala.scalajs.js
import js.Dynamic.{global => g, newInstance => jsnew}

import com.example.protos.hello._
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global

object HelloWorldClientSjs extends App {

  val proto = grpc.load("../server/src/main/protobuf/hello.proto").com.example.protos

  val server = jsnew(grpc.Server)().asInstanceOf[js.Dynamic]
  
  server.addService(
    proto.Greeter.service,
    GreeterGrpc.bindService(new GreeterImpl, ExecutionContext.global)
  )
  server.bind("0.0.0.0:5050", grpc.ServerCredentials.createInsecure())

  private class GreeterImpl extends GreeterGrpc.Greeter {
    override def sayHello(req: HelloRequest) = {
      val reply = HelloReply(message = "Hello " + req.name)
      scala.concurrent.Future.successful(reply)
    }
  }

  println("starting server")
  server.start()
}

import scala.scalajs.js.annotation.JSImport

@JSImport("grpc", JSImport.Namespace)
@js.native
object grpc extends js.Object {
  def load(path: String): js.Dynamic = js.native
  val credentials: js.Dynamic = js.native

  val Server: js.Dynamic = js.native
  val ServerCredentials: js.Dynamic = js.native
}
