import io.grpc.{Server, ServerBuilder}

import com.example.protos.hello._

import scala.concurrent.{ExecutionContext, Future}

object HelloWorldServer extends App {

  val server = ServerBuilder
    .forPort(5050)
    .addService(
      GreeterGrpc.bindService(new GreeterImpl, ExecutionContext.global)
    )
    .build
    .start

  private class GreeterImpl extends GreeterGrpc.Greeter {
    override def sayHello(req: HelloRequest) = {
      val reply = HelloReply(message = "Hello " + req.name)
      Future.successful(reply)
    }
  }

  scala.io.StdIn.readLine()
  println("quitting")
  
  server.shutdown()
}
