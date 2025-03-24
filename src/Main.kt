fun main() {
    println("Hello World!")

    val age =22
    val name ="laura"

    if(age<=18) println("$name can't buy alcohol") else println("$name can buy alcohol")
    val numbers= intArrayOf(2,7,11,15)
    val target =9

    val operation=Solution()
    println(operation.sum(numbers,target).contentToString())

    //creating and linking nodes

        val node1 = Node(1)
        val node2 = Node(2)
        val node3 = Node(3)
        val node4 = Node("uno")
        val node5 = Node("dos")
        val node6 = Node("tres")

        node1.next = node2
        node2.next = node3

        node4.next = node5
        node5.next = node6

        println(node2)
        println(node4)

    val list = LinkedList<Int>()
    list.push(3)
    list.push(2)
    list.push(1)

    println(list)

    list.append(1)
    list.append(2)
    list.append(3)

    println(list)
//Inserting Nodes
    println("Before Inserting $list")
    var middleNode = list.nodeAt(1)!!
    for (i in 1..3){
        middleNode = list.insert(-1*i,middleNode)
    }
    println("After inserting $list")
//Removing Nodes
    println("Before popping $list")
    var poppedValue = list.pop()
    println("After popping $list")
    println("Popped value: $poppedValue")
}
class Solution(
)
{
    fun sum(nums:IntArray,target:Int):IntArray {
        for (i in nums.indices) {
            for (j in i + 1 ..  nums.size) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }

            }
        }
        return intArrayOf()

    }

}
// Data Structures & Algorithms in Kotlin
//Adding Nodes
data class Node<T>(var value: T, var next:Node<T>?=null){
    override fun toString(): String {
        return if (next != null){
            "$value -> ${next.toString()}"
        }else{
            "$value"
        }
    }
}

class LinkedList<T>{
    private var head:Node<T>?=null
    private var tail:Node<T>?=null
    private var size = 0

    fun isEmpty():Boolean{
        return size ==0
    }

    override fun toString(): String {
        if (isEmpty()) {
            return "Empty list"
        } else {
            return head.toString()
        }
    }
    fun push(value:T){
        head = Node(value = value, next = head)
        if(tail==null){
            tail=head
        }
        size++
    }
    fun append(value:T){
        if(isEmpty()){
            push(value)
            return
        }
        tail?.next = Node(value=value)

        tail=tail?.next
        size++
    }
    fun nodeAt(index:Int):Node<T>?{
        var currentNode = head
        var currentIndex = 0

        while(currentNode!=null && currentIndex < index){
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }
    fun insert(value:T , afterNode: Node<T>): Node<T>{
        if(tail==afterNode){
            append(value)
            return tail!!
        }
        val newNode = Node(value= value, next = afterNode.next)
        afterNode.next = newNode
        size++
        return newNode
    }

    //Removing nodes

        //Removing first node
    fun pop():T? {
        if (!isEmpty()) size--
        val result = head?.value
        head = head?.next
        if (isEmpty()) {
            tail = null
        }
    return result
    }
        //Removing last node
    


}
To solve the problem of adding two numbers represented by linked lists, we need to consider the following:

Each linked list represents a number, where the digits are stored in reverse order. For example, the list [2, 4, 3] represents the number 342 (since the digits are in reverse order).
We need to iterate over both linked lists simultaneously, add the corresponding digits, and take care of the carry if the sum exceeds 9.
The result should also be a linked list where the digits are in reverse order.
Plan:
We will traverse both linked lists, adding corresponding digits from l1 and l2, and maintain a carry value.
If the sum of two digits exceeds 9, we will set the carry to 1 and store the digit (sum % 10) in the result list.
If there is a carry left after both lists are completely traversed, we will add a final node with the carry value.
The resulting linked list will be returned.
Code Implementation:
kotlin
Copy
class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var p1 = l1 // Pointer for first list
        var p2 = l2 // Pointer for second list
        var carry = 0 // Carry for the sum of digits
        var dummyHead = ListNode(0) // Dummy node to start the result list
        var current = dummyHead // Pointer to build the result list

        // Iterate through both lists until both are null and there's no carry left
        while (p1 != null || p2 != null || carry != 0) {
            val val1 = p1?.`val` ?: 0 // If p1 is null, treat its value as 0
            val val2 = p2?.`val` ?: 0 // If p2 is null, treat its value as 0

            val sum = val1 + val2 + carry // Calculate the sum
            carry = sum / 10 // Update the carry for next iteration
            current.next = ListNode(sum % 10) // Create a new node with the current digit
            current = current.next!! // Move the current pointer

            // Move p1 and p2 to the next nodes, if possible
            p1 = p1?.next
            p2 = p2?.next
        }

        // Return the result list starting from the next of dummyHead
        return dummyHead.next
    }
}
Explanation:
Dummy node: We use a dummy node (dummyHead) to simplify the logic. It helps avoid edge cases when we are building the result list.
Traversal: We use two pointers (p1 for l1 and p2 for l2) to traverse through both lists. For each node, we calculate the sum of the digits and the carry from the previous iteration.
Carry: We calculate the carry for the next iteration (carry = sum / 10). The value to store in the result node is sum % 10.
Result construction: We create new nodes in the result list using the sum modulo 10. The pointer current helps to keep track of where to add the next digit in the result list.
Edge case handling: If either l1 or l2 has remaining digits or there's a carry left after the iteration ends, we handle that by continuing the loop.
Example:
For the input:

l1 = [2, 4, 3] (represents 342)
l2 = [5, 6, 4] (represents 465)
The steps of the algorithm will add digits from l1 and l2:

2 + 5 = 7 (no carry)
4 + 6 = 10 → store 0, carry 1
3 + 4 + 1 (carry) = 8 (no carry)
Thus, the resulting linked list will be [7, 0, 8], which represents the sum 807.

Time Complexity:
The time complexity is O(max(N, M)), where N and M are the lengths of the two linked lists. We iterate through each list once.
Space Complexity:
The space complexity is O(max(N, M)) since we are creating a new linked list to store the result.


