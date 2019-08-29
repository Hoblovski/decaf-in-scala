package decaf.tac

import decaf.annot._

/**
  * TAC "library" procedures.
  */
object Lib {

  /**
    * Procedure descriptor/signature.
    *
    * @param name       procedure name
    * @param numArgs    number of input arguments
    * @param returnType return type
    */
  case class Intrinsic(name: String, numArgs: Int, returnType: BaseType) {
    val label: Label = Label.fresh(name)
  }

  implicit def getLabelOfIntrinsic(self: Intrinsic): Label = self.label

  object ALLOCATE extends Intrinsic("_Alloc", 1, IntType)

  object READ_LINE extends Intrinsic("_ReadLine", 0, StringType)

  object READ_INT extends Intrinsic("_ReadInteger", 0, IntType)

  object STRING_EQUAL extends Intrinsic("_StringEqual", 2, BoolType)

  object PRINT_INT extends Intrinsic("_PrintInt", 1, VoidType)

  object PRINT_STRING extends Intrinsic("_PrintString", 1, VoidType)

  object PRINT_BOOL extends Intrinsic("_PrintBool", 1, VoidType)

  object HALT extends Intrinsic("_Halt", 0, VoidType)

}