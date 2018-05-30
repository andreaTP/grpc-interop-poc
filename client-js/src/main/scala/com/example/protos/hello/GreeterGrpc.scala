package com.example.protos.hello

object GreeterGrpc {
  
  trait Greeter extends _root_.scalapb.grpc.AbstractService {
    override def serviceCompanion = Greeter
    def sayHello(request: com.example.protos.hello.HelloRequest): scala.concurrent.Future[com.example.protos.hello.HelloReply]
  }
  
  object Greeter extends _root_.scalapb.grpc.ServiceCompanion[Greeter] {
    implicit def serviceCompanion: _root_.scalapb.grpc.ServiceCompanion[Greeter] = this
    def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = com.example.protos.hello.HelloProto.javaDescriptor.getServices().get(0)
  }

  import scala.concurrent.{Future, Promise}
  
  class GreeterStub(client: scala.scalajs.js.Dynamic) extends Greeter {
    def sayHello(request: com.example.protos.hello.HelloRequest): scala.concurrent.Future[com.example.protos.hello.HelloReply] = {
      val p = Promise[com.example.protos.hello.HelloReply]()

      import scala.scalajs.js.JSConverters._

      val msg =
        request.toPMessage.value.map{case (k,v) => (k.name, v.as[String])}.toJSDictionary

      client.sayHello(msg, (err: scala.scalajs.js.Dynamic, resp: scala.scalajs.js.Dynamic) => {
        if (err == null && !scala.scalajs.js.isUndefined(resp)) {

          val msg = new com.example.protos.hello.HelloReply()
            .withMessage(resp.message.toString)

          p.success(msg)
        } else if (!scala.scalajs.js.isUndefined(err) && err != null) {
          p.failure(err.asInstanceOf[Throwable])
        } else {
          p.failure(new Exception("Unknown error"))
        }
      })

      p.future
    }
  }
  
  def bindService(serviceImpl: Greeter, executionContext: scala.concurrent.ExecutionContext): scala.scalajs.js.Dynamic =
    scala.scalajs.js.Dynamic.literal(
      "sayHello" -> {
        (call: scala.scalajs.js.Dynamic, callback: scala.scalajs.js.Dynamic) =>
          import scala.scalajs.js.JSConverters._

          val msg = new com.example.protos.hello.HelloRequest()
            .withName(call.request.name.toString)

          serviceImpl.sayHello(msg).onComplete{
            case scala.util.Success(res) =>
              callback(
                null,
                res.toPMessage.value.map{case (k,v) => (k.name, v.as[String])}.toJSDictionary
              )
            case scala.util.Failure(err) =>
              callback(
                err.asInstanceOf[scala.scalajs.js.Any], null
              )
          }(executionContext)
      }
    )

  def stub(client: scala.scalajs.js.Dynamic): GreeterStub = new GreeterStub(client)
  
  def javaDescriptor: _root_.com.google.protobuf.Descriptors.ServiceDescriptor = com.example.protos.hello.HelloProto.javaDescriptor.getServices().get(0)
  
}
