  // GENERATED
/* INSTRUCTIONS
 *
 * Complete the exercises below.  For each "EXERCISE" comment, add
 * code immediately below the comment.
 *
 * Please see README.md for instructions, including compilation and testing.
 *
 * GRADING
 *
 * 1. Submissions MUST compile using SBT with UNCHANGED configuration and
 *    tests with no compilation errors.  Submissions with compilation errors
 *    will receive 0 points.  Note that refactoring the code will cause the
 *    tests to fail.
 *
 * 2. You MUST NOT edit the SBT configuration and tests.  Altering it in your
 *    submission will result in 0 points for this assignment.
 *
 * 3. You MUST NOT use while loops or (re)assignment to variables (you can
 *    use "val" declarations, but not "var" declarations).  You must use
 *    recursion instead.
 *
 * 4. You may declare auxiliary functions if you like.
 *
 * SUBMISSION
 *
 * 1. Submit this file on D2L before the deadline.
 *
 * 2. Late submissions will not be permitted because solutions will be
 *    discussed in class.
 *
 */

import java.util.NoSuchElementException
object fp2:

  // EXERCISE 1: complete the following recursive definition of a "map"
  // function for Scala's builtin List type.  You must not use the builtin
  // "map" method.
  //
  // Your implementation of "map" MUST be recursive.
  def map[A, B](xs: List[A], f: A => B): List[B] = xs match
    case Nil => Nil
    case y :: ys => f(y) :: map(ys, f)

  // EXERCISE 2: complete the following recursive definition of a "filter"
  // function for Scala's builtin List type.  You must not use the builtin
  // "filter" method.
  //
  // Your implementation of "filter" MUST be recursive.
  def filter[A](xs: List[A], f: A => Boolean): List[A] = xs match
    case Nil => Nil
    case y::ys if f (y) => y :: filter(ys, f) // if y passes boolean, keep and continue
    case y::ys => filter(ys, f) // y is false, continue with remaining list
    // can also write
              // case y::ys => if f(y) then y::filter(ys, f) else filter (ys, f)

  // EXERCISE 3: complete the following recursive definition of an "append"
  // function for Scala's builtin List type.  You must not use the builtin
  // ":::" method.
  //
  // Your implementation of "append" MUST be recursive.
  //
  // HINT: use "::" in the body of the cons-cell case.
  def append[A](xs: List[A], ys: List[A]): List[A] = xs match
    case List() => ys // empty list
    case first::next =>  first :: append(next, ys) // goes through entire xs list, appending to "first" element
                                                   // and then to ys, xs is then empty
                                                   // returns all of ys (or an empty list if ys is empty) 

  // EXERCISE 4: complete the following recursive definition of a "flatten"
  // function for Scala's builtin List type.  You must not use the builtin
  // "flatten" method.
  //
  // Your implementation of "flatten" MUST be recursive.
  //
  // HINT: use either ":::" or your definition of "append" in the body of the
  // cons-cell case.
  //
  // EXAMPLE:
  // - flatten (List ((1 to 5).toList, (6 to 10).toList, (11 to 15).toList)) == (1 to 15).toList
  def flatten[A](xss: List[List[A]]): List[A] = xss match
    case List() => Nil // empty list
    case x :: xs => append(x, flatten(xs)) // result of call to flatten is second list for call to append
                                        

  // EXERCISE 5: complete the following recursive definition of a "foldLeft"
  // function for Scala's builtin list type.  You must not use the builtin
  // "foldLeft" method.
  //
  // Your implementation of "foldLeft" MUST be recursive.
  //
  // HINT:   foldLeft (  Nil, e, f) == e
  //         foldLeft (y::ys, e, f) == foldLeft (ys, f (e, y), f)
  def foldLeft[A, B](xs: List[A], e: B, f: (B, A) => B): B = xs match
    case Nil => e
    case y::ys => foldLeft(ys, f(e,y), f)

  // EXERCISE 6: complete the following recursive definition of a "foldRight"
  // function for Scala's builtin list type.  You must not use the builtin
  // "foldRight" method.
  //
  // Your implementation of "foldRight" MUST be recursive.
  //
  // HINT:   foldRight (  Nil, e, f) == e
  //         foldRight (y::ys, e, f) == f (y, foldRight (ys, e, f))
  def foldRight[A, B](xs: List[A], e: B, f: (A, B) => B): B = xs match
    case Nil => e
    case y::ys => f(y, foldRight(ys, e, f))

  // EXERCISE 7: complete the following definition of a "joinTerminateRight"
  // function to take a list of strings "xs" and concatenate all strings
  // using a string "term" as a terminator (not delimiter) between strings.
  //
  // You MUST use your foldRight defined above.
  //
  // You MAY NOT use recursion.
  //
  // EXAMPLES:
  // - joinTerminateRight (Nil, ";") == ""
  // - joinTerminateRight (List ("a"), ";") == "a;"
  // - joinTerminateRight (List ("a","b","c","d"), ";") == "a;b;c;d;"
  def joinTerminateRight(xs: List[String], term: String): String =
    foldRight(xs, "", (a: String, b: String) => a+term+b)

  // EXERCISE 8: complete the following definition of a "joinTerminateLeft"
  // function to take a list of strings "xs" and concatenate all strings
  // using a string "term" as a terminator (not delimiter) between strings.
  //
  // You MUST use your foldLeft defined above.
  //
  // You MAY NOT use recursion.
  //
  // EXAMPLES:
  // - joinTerminateLeft (Nil, ";") == ""
  // - joinTerminateLeft (List ("a"), ";") == "a;"
  // - joinTerminateLeft (List ("a","b","c","d"), ";") == "a;b;c;d;"
  def joinTerminateLeft(xs: List[String], term: String): String =
    foldLeft(xs, "", (a:String, b:String)=> a+b+term)

  // EXERCISE 9: complete the following recursive definition of a
  // "firstNumGreaterThan" function to find the first number greater than or
  // equal to "a" in a list of integers "xs".
  //
  // If the list is empty or there is no number greater than or equal to "a",
  // throw a java.util.NoSuchElementException (with no argument).
  //
  // Your implementation of "firstNumGreaterThan" MUST be recursive.
  //
  // EXAMPLES:
  // - firstNumGreaterThan (5, List (4, 6, 8, 5)) == 6
  def firstNumGreaterThan(a: Int, xs: List[Int]): Int =
    if xs.isEmpty then // empty list / no element >= a
      throw java.util.NoSuchElementException()
    
    val num = xs.head
    if num >= a then // check first element in list
        num
    else
      firstNumGreaterThan(a, xs.tail) // keep looking through list

  // EXERCISE 10: complete the following recursive definition of a
  // "firstIndexNumGreaterThan" function to find the index (position) of the
  // first number greater than or equal to "a" in a list of integers "xs".
  //
  // The first index should be zero (not one).
  //
  // If the list is empty or there is no number greater than or equal to "a",
  // throw a java.util.NoSuchElementException (with no argument).
  //
  // Your implementation of "firstIndexNumGreaterThan" MUST be recursive.
  //
  // EXAMPLES:
  // - firstIndexNumGreaterThan (5, List (4, 6, 8, 5)) == 1
  //
  // HINT: this is a bit easier to write if you use an auxiliary function.
  def firstIndexNumGreaterThan(a: Int, xs: List[Int]): Int =
    val num = firstNumGreaterThan(a, xs)
    xs.indexOf(num)

  

