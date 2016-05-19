package org.test

import scala.concurrent.Future

object TestFeature {

  val FutureSuccess: Future[String] = Future.successful("future successful")
  val FutureFailed: Future[String] = Future.failed(FailureException)
  val FutureFailedRuntime: Future[String] = Future.failed(new RuntimeException(""))
  val ListOfString = "aa" :: "" :: "babbo" :: "none" :: "b" :: Nil
  val ListOfOptionString = Some("aa") :: None :: Some("babbo") :: Some("none") :: None :: Nil
  val ListOptionInt = None :: Some(1) :: Some(87) :: None :: Some(-3) :: Nil
  val FailureException = new Exception("future failed")
  val FailureExceptionCause = new Exception("future failed", new NumberFormatException("wrong number"))


}
