import java.io.File
import java.util.Stack

/**
 * https://www.hackerrank.com/challenges/2d-array/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 */
fun hourglassSum(arr: Array<Array<Int>>): Int {
    var max = -100
    for(i in 0..arr.size-3) {
        for(j in 0..arr.size-3) {
            //hourglass starting (i,j)
            var sum =  arr[i][j] + arr[i][j+1] + arr[i][j+2]
            sum +=  arr[i+1][j+1]
            sum +=  arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2]
            if(sum  > max) max = sum
        }
    }
    return max

}

fun rotLeft(a: Array<Int>, d: Int): Array<Int> {
    // Write your code here
    val n = a.size
    val newArray = Array<Int>(n) {0}
    var i = 0
    while(i < n) {
        newArray[i] = a[(d%n+i)%n]
        i++
    }
    return newArray
}

/**
 * https://www.hackerrank.com/challenges/crush/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 */
fun arrayManipulation(n: Int, queries: Array<Array<Int>>): Long {
    // Write your code here
    val arr = MutableList<Long>(n) {0}
    var max : Long = 10
    for(query in queries) {
        for (i in query[0]-1 until query[1]) {
            arr.set(i,arr[i] + query[2])
        }
    }
    arr.forEach { (if (it > max) max = it) }
    return  max
}

fun checkMagazine(magazine: Array<String>, note: Array<String>): Unit {
    // Write your code here
    val map = HashMap<String,Int>()
    note.forEach { w ->
        if(map.containsKey(w)) map[w] = map[w]!! + 1
        else map[w] = 1
    }

    magazine.forEach { w ->
        if(map.containsKey(w)) map[w] = map[w]!! - 1
    }

    var max = -1
    for( value in map.values) { if(value > max) max = value }

    if(max > 0) println("No")
    else println("Yes")
}


fun generateSubSet() {

}

/** Complete the 'sherlockAndAnagrams' function below.
*
* The function is expected to return an INTEGER.
* The function accepts STRING s as parameter.
 * Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string.
 * Given a string, find the number of pairs of substrings of the string that are anagrams of each other.
 *
 * ex : mom [m,m] and [mo,om] => 2
 * TODO
 */

fun sherlockAndAnagrams(s: String): Int {
    val ltrFreqs = HashMap<Char,Int>()
    s.forEach {
        if(ltrFreqs.containsKey(it)) ltrFreqs[it] = ltrFreqs[it]!! +  1
        else ltrFreqs[it] = 1
    }
    val ignoredIndex = HashSet<Int>()
    for(i in 0 until s.length) {
        if(ltrFreqs.getOrDefault(s[i],0) <=1 ) {
            ignoredIndex.add(i)
        }
    }


    return 0
}

/**
 * prices of toys
 * k : budget
 * return max numlberr of toys that you can buy v(one toy per price)
 */
fun maximumToys(prices: Array<Int>, k: Int): Int {
    // Write your code here
    prices.sort()
    var i = 0
    var budget = k
    while(i< prices.size && budget > prices[i]) {
        budget -= prices[i]
        i++
    }

    return i
}

fun insertSorted(medianWindow: MutableList<Int>, item : Int) : Int {
    var i = 0
    while ( i < medianWindow.size && medianWindow[i] < item) i++
    medianWindow.add(i,item)
    return i
}

fun removeSorted(medianWindow: MutableList<Int>, item: Int) {
    var rIndex = medianWindow.lastIndex
    var lIndex = 0
    var found = false
    while (rIndex > lIndex && !found) {
        var midIndex = lIndex + (rIndex - lIndex)/2
        if(medianWindow[midIndex] == item) {
            medianWindow.removeAt(midIndex)
            found = true
        }else if( medianWindow[midIndex] > item) {
            rIndex = midIndex - 1
        }else {
            lIndex  = midIndex + 1
        }
    }

}

fun getMedian(medianWindow: MutableList<Int>) : Double {
    val n= medianWindow.size
    if(n%2==1) {
        return medianWindow[n/2].toDouble()
    }else {
        return (medianWindow[n/2] + medianWindow[n/2-1]) / 2.0
    }
}



/**
 * https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
 *
 */
fun activityNotifications(expenditure: Array<Int>, d: Int): Int {
    val medianWindow = expenditure.slice(0 until d).toMutableList()
    medianWindow.sort()
    var countNotif = 0
    val insertedIndex = MutableList<Int>(d) {0}
    for(i in d..expenditure.lastIndex) {
        val median = getMedian(medianWindow)
        //println("Median : $median")
        if( expenditure[i] >= 2*median ) countNotif++
        removeSorted(medianWindow, expenditure[i-d])
        insertSorted(medianWindow,expenditure[i])
    }
    return  countNotif

}

/**
 * https://www.hackerrank.com/challenges/ctci-making-anagrams/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings
 */
fun makeAnagram(a: String, b: String): Int {
    // Write your code here
    var delta = 0
    val freqA = Array<Int>(26) {0}
    val freqB = Array<Int>(26) {0}
    a.forEach {
        val index = ( it - 97 ).code
        freqA[index] += 1
    }

    b.forEach {
        val index = ( it - 97 ).code
        freqB[index] += 1
    }

    for( i in 0..freqA.lastIndex) {
        delta += Math.abs(freqA[i] - freqB[i])
    }
    return delta

}

fun minimumAbsoluteDifference(arr: Array<Int>): Int {
    arr.sort()
    var min = Math.abs(arr[0])
    for ( i in 1 .. arr.lastIndex) {
        if( min > arr[i] - arr[i-1]) {
            min = arr[i] - arr[i-1]
        }
    }
    return min

    // Write your code here

}

fun luckBalance(k: Int, contests: Array<Array<Int>>): Int {
    // Write your code here
    val sumWeightNonImp = contests.filter { it[1] == 0 }.map { it[0] }.sum()
    val weightsImp = contests.filter { it[1] == 1 }.map { it[0] }.toMutableList()
    weightsImp.sort()
    var sumWeightImp = 0
    var i = weightsImp.lastIndex
    while( i >=0 && i >= weightsImp.size - k   ) {
        sumWeightImp += weightsImp[i]
        i--
    }
    return  sumWeightImp + sumWeightNonImp
}


fun maxMin(k: Int, arr: Array<Int>): Int {
    // Write your code here
    arr.sort()
    var min = arr[arr.lastIndex]
    for(i in 0..(arr.size-k)) {
        val delta = arr[i+k-1] - arr[i]
        if(delta < min) min = delta
    }
    return min
}

fun swap(arr: Array<Int>, i : Int, j : Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}

fun quickSort(arr : Array<Int>, indices : Array<Int>, left : Int, right: Int) {
    if( left < right) {
        val pivot = arr[right]
        var pivotIndex = left

        for(i in left until right) {
            if(arr[i] <= pivot) {
                swap(arr,i,pivotIndex)
                swap(indices,i,pivotIndex)
                pivotIndex++
            }
        }
        swap(arr,pivotIndex,right)
        swap(indices,pivotIndex,right)

        quickSort(arr,indices,left,pivotIndex-1)
        quickSort(arr,indices,pivotIndex+1,right)

    }


}

/**
 * https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
 */
fun whatFlavors(cost: Array<Int>, money: Int): Unit {
    // Write your code here
    val set = HashMap<Int,Int>()
    var value1Index = -1
    cost.forEach {
        if(set.containsKey(it)) set[it] = set.get(it)!!.plus(1)
        else set.put(it,1)
    }
    var i = 0
    var found = false
    while(i < cost.size && !found) {
        if(set.containsKey(money - cost[i])) {
            if(money%2==0 && cost[i] == money/2) { //ex money = 8 and there is only one 4 so no solution with it
                if(set[cost[i]]!! > 1) {
                    value1Index = i
                    found = true
                }
            }else {
                value1Index = i
                found = true
            }
        }
        i++
    }
    while( i < cost.size && cost[i] != money - cost[value1Index]) i++
    println("${value1Index+1} ${i+1}")

}


/**
 *https://www.hackerrank.com/challenges/pairs/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
 */
fun pairs(k: Int, arr: Array<Int>): Int {
    // Write your code here
    var nPairs = 0
    val frequencies = HashMap<Int,Int>()
    arr.forEach {
        if(frequencies.containsKey(it)) frequencies[it] = frequencies.get(it)!!.plus(1)
        else frequencies.put(it,1)
    }

    arr.forEach {
        if( frequencies.containsKey(it + k) ) {
            nPairs += frequencies[it + k]!!
        }
    }
    return nPairs

}

/**
 * https://www.hackerrank.com/challenges/max-array-sum/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
 * TODO
 */
fun maxSubsetSum(arr: Array<Int>): Int {
    val n = arr.size
    if(n == 1) {
        return Math.max(0,arr[0])
    }else if( n == 2) {
        return Math.max(0,Math.max(arr[0],arr[1]))
    }else {
        val n1 = maxSubsetSum( arr.sliceArray(0..n/2) )
        val n2 = maxSubsetSum( arr.sliceArray(n/2 + 1 until n) )
        //TODO
        return Math.max(n1,n2)
    }
}

fun isBalanced(s: String): String {
    val brackets = Stack<Char>()
    for( c in s) {
        if( c == '[' || c == '(' || c == '{') {
            brackets.push(c)
        }else {
            if(brackets.empty()) return "NO"

            val opening  = brackets.pop()
            if(  (opening != '(' && c == ')' ) || ( c == ']' && opening != '['  ) ||  ( c == '}' && opening != '{'  ) ) return "NO"
        }
    }

    if(brackets.isNotEmpty()) return "NO"
    return "YES"

}

fun main(args: Array<String>) {
    whatFlavors(arrayOf(4,3,2,5,7),8)

}

fun main2(args: Array<String>) {
    val lines = File("input04.txt").readLines()

    val first_multiple_input = lines[0]!!.trimEnd().split(" ")

    val n = first_multiple_input[0].toInt()

    val d = first_multiple_input[1].toInt()

    val expenditure = lines[1]!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val result = activityNotifications(expenditure, d)

    println(result)
}