package decaf.driver

object Launcher {
  def withArgs(args: Array[String]): Unit = {
    OptParser.parse(args, new Config) match {
      case Some(config) => withConfig(config)
      case None => // exit
    }
  }

  def withConfig(implicit config: Config): Unit = {
    val tasks = new Tasks
    val task = config.target match {
      case Config.Target.jvm => tasks.jvm
      case Config.Target.PA1 => tasks.parse
      case Config.Target.PA2 => tasks.typeCheck
      case Config.Target.PA3 => tasks.tac
    }
    task.run(config.source)
  }
}