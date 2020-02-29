package com.blogspot.soyamr.exchangerate.model.RecyclerViewCompenent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.soyamr.exchangerate.Utils
import com.blogspot.soyamr.exchangerate.Controller.MapSetUp
import com.blogspot.soyamr.exchangerate.R
import com.blogspot.soyamr.exchangerate.model.Pojo.ATM
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.atm_list_item_row.view.*

class AtmsRecyclerViewAdapter(private val myDataset: ArrayList<ATM>, val map: MapSetUp) :
        RecyclerView.Adapter<AtmsRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        // create a new view
        val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.atm_list_item_row, parent, false) as View

        return MyViewHolder(inflatedView,map)
    }

    override fun getItemCount(): Int = myDataset.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemATM = myDataset[position]
        holder.bindAtm(itemATM)
    }

    //1
    class MyViewHolder(val view: View, map: MapSetUp) : RecyclerView.ViewHolder(view) {
        //2
        private var atm: ATM? = null

        //3
        init {
            view.setOnClickListener{
                val currentLatLng = LatLng(atm?.lat!!.toDouble(), atm?.lon!!.toDouble())
                map.showAtm(currentLatLng)
            }
        }

        fun bindAtm(atm: ATM) {
            this.atm = atm
            view.workingHours.append(" "+atm.hours_start+"-"+atm.hours_end)
            view.address.text = atm.address
            view.type.text = atm.type
            val isWorking :Boolean = Utils.isWorkingNow(atm.hours_start,atm.hours_end)

            view.isWorking.text = if(isWorking) view.resources.getString(R.string.working)
            else view.resources.getString(R.string.notWorking)
            if(isWorking)
                view.isWorking.setTextColor(view.resources.getColor(R.color.workingColor))
            else
                view.isWorking.setTextColor(view.resources.getColor(R.color.isntWorkingColor))

        }

    }

}