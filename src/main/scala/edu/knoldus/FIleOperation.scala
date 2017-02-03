package edu.knoldus

import java.io.File

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FileOperation {

  def getListOfFilesAndFolders(srcDirectory: String): Future[List[File]] = Future {

    val f = new File(srcDirectory)
    val listDirectory = f.listFiles().filter(_.isDirectory) toList
    val listFile = f.listFiles().filter(_.isFile).toList
    getSubsequentPaths(listDirectory, listFile)
  }

  def getSubsequentPaths(directoryList: List[File], fileList: List[File]): List[File] = {
    directoryList match {
      case Nil => fileList
      case x :: Nil => val dir = x.listFiles().filter(_.isDirectory).toList
        val file = x.listFiles().filter(_.isFile).toList
        getSubsequentPaths(dir, fileList ::: file)
      case x :: xs =>
        val dir = x.listFiles().filter(_.isDirectory).toList
        val file = x.listFiles().filter(_.isFile).toList
        getSubsequentPaths(dir, file)
    }
  }

  def checkValidDirectory(srcDirectory: String): Boolean = {
    val f = new File(srcDirectory)
    if (f.isDirectory) {
      true
    }
    else {
      false
    }
  }

}

