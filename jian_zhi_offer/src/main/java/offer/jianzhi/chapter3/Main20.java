package offer.jianzhi.chapter3;

/**
 * 输入一个矩阵，按照从外到里顺时针的顺序打印出每一个数字
 *
 * @author jhZhang
 * @date 2018/5/15
 */
public class Main20 {
public static void main(String[] args) {
int columns = 4;
int[][] numbers = new int[columns][columns];
for (int i = 0; i < columns; i++) {
for (int j = 0; j < columns; j++) {
numbers[i][j] = i * columns + j + 1;
System.out.printf("%d\t", i * columns + j + 1);
}
System.out.println();
}
new Main20().printMatrixInCircle(numbers, columns, columns);
}

void printMatrixInCircle(int[][] numbers, int columns, int rows) {
if (numbers == null || columns <= 0 || rows <= 0) {
return;
}
int start = 0;
while (columns > start * 2 || rows > start * 2) {
printMatrixInCircle(numbers, columns, rows, start);
start++;
}
}

void printMatrixInCircle(int[][] numbers, int columns, int rows, int start) {
int endX = columns - 1 - start;
int endY = rows - 1 - start;
// 从左到右打印一行
for (int i = start; i <= endX; i++) {
System.out.printf("%d\t", numbers[start][i]);
}
// 从上到下打印一列
if (start < endY) {
for (int i = start + 1; i <= endY; i++) {
System.out.printf("%d\t", numbers[i][endX]);
}
}
// 从右到左打印一行
if (start < endX && start < endY) {
for (int i = endX - 1; i >= start; i--) {
System.out.printf("%d\t", numbers[endY][i]);
}
}
// 从下到上打印一列
if (start < endX && start < endY - 1) {
for (int i = endY - 1; i >= start + 1; i--) {
System.out.printf("%d\t", numbers[i][start]);
}
}
}
}
