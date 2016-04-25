package org.test

import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global


object FutureTest {

  def concatenateNTimes (times: Int)(input: String): String = {
    @tailrec
    def concatenate(times: Int, acc: String): String = {
      if(times < 1 || times == 0) acc
      else concatenate(times - 1, acc + input)
    }

    if(input == null) input
    else concatenate(times, input)
  }


  def concatenateNTimesFutures (times: Int)(input: String): Future[String] = {
    Future(concatenateNTimes(times)(input))
  }

  def getFirstFibonacci (index: Int): Future[String] = {
    Future{
      println("@@ "+index+" @@ getFirstFibonacci started")
      Thread.sleep(20)
      val pio = fib(index).toString
      println("@@@@ Finished")
      pio
    }
  }


  def getFirstNFibonacci (n: Int): Future[String] = {
    Future{
      println("## "+n+" ## getFirstNFibonacci started")
      Thread.sleep(10)
      val pio = fib.take(n).foldLeft("")((acc, number) => {acc + number + ","})
      println("#### Finished")
      pio
    }
  }

  def test(i: Int): Future[String] = {
    val fib = getFirstFibonacci(i)
    val firstFib = getFirstNFibonacci(i)
    val concatenate = concatenateNTimesFutures(i)_

    for {
      numFib <- fib
      numsFib <- firstFib
      res <- concatenate(numFib+"@"+numsFib)
    } yield res
  }

  lazy val fib: Stream[Long] = {
    def tail(h: Long, n: Long): Stream[Long] = h #:: tail(n, h + n)
    tail(0, 1)
  }
}
