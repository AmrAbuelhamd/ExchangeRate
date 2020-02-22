package com.blogspot.soyamr.exchangerate.View

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.exchangerate.Controller.Controller
import com.blogspot.soyamr.exchangerate.Controller.MapSetUp
import com.blogspot.soyamr.exchangerate.R
import com.blogspot.soyamr.exchangerate.model.Pojo.ATM
import com.blogspot.soyamr.exchangerate.model.RecyclerViewCompenent.AtmsRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_atms.*

class BranchesAndATMsActivity : ViewParent() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var controller: Controller
    private val myDataSet = ArrayList<ATM>()
    lateinit var map:MapSetUp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atms)

        map = MapSetUp(this)
        controller = Controller(this)
        controller.getATMdata()
        initializeRecyclerView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        map.onActivityResult(requestCode, resultCode, data)
    }

    private fun initializeRecyclerView() {
        viewManager = LinearLayoutManager(this)

        viewAdapter = AtmsRecyclerViewAdapter(myDataSet,map)

        atm_recycler_view.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    override fun <T : Any?> updateRecyclerViewData(dataList: MutableList<T>?) {
        myDataSet.clear()
        myDataSet.addAll(dataList as Collection<ATM>)
        viewAdapter.notifyDataSetChanged()
    }


}
