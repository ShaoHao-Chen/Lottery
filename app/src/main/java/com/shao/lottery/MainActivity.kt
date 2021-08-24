package com.shao.lottery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class MainActivity : AppCompatActivity() {

    private lateinit var data: LinkedList<HashMap<String, String>>
    private lateinit var mAdapter: SimpleAdapter
    private val from = arrayOf("title", "num1", "num2", "num3", "num4", "num5", "num6")
    private val to = intArrayOf(R.id.item_title, R.id.item_n1, R.id.item_n2, R.id.item_n3,
            R.id.item_n4, R.id.item_n5, R.id.item_n6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        btn_draw.setOnClickListener {
            val lottery: IntArray = createLottery()
            val row = HashMap<String, String>()
            row[from[0]] = "第" + (data.size+1) + "組"
            row[from[1]] = lottery[0].toString()
            row[from[2]] = lottery[1].toString()
            row[from[3]] = lottery[2].toString()
            row[from[4]] = lottery[3].toString()
            row[from[5]] = lottery[4].toString()
            row[from[6]] = lottery[5].toString()
            data.add(row)
            mAdapter.notifyDataSetChanged()
            list_result.smoothScrollToPosition(data.size-1)
        }
    }

    private fun initView() {
        data = LinkedList()
        mAdapter = SimpleAdapter(this, data, R.layout.item, from, to)
        list_result.adapter=mAdapter
    }
    private fun createLottery():IntArray{
        val numbs = HashSet<Int>()
        while(numbs.size<6){
            numbs.add((Math.random()*49+1).toInt())
        }

        val ret = IntArray(6)
        for((idx, value) in numbs.withIndex()){
            ret[idx]=value
        }
        return ret
    }
}