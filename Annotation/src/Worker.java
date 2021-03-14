/*
*如果定义属性，使用default给属性默认初始化，可以不进行属性赋值
* 如果只有一个属性需要赋值，并且属性的名称是value,value可以省略，直接定义值即可
* 数组赋值时，使用{}，如果数组只有一个值，则{}可以省略
*/
@MyAnnotation(show1 = 12, per = Person.P1, anno = @BasicAnnaotaion, showArray2 = "123")
public class Worker {
}
