package com.example.todolistapp.view.framgments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R
import com.example.todolistapp.model.storage.UserName

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var userList = emptyList<UserName>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName = itemView.findViewById<AppCompatTextView>(R.id.user_name_Tv)
        val userId = itemView.findViewById<AppCompatTextView>(R.id.user_id_Tv)
        val item = itemView.findViewById<LinearLayout>(R.id.current_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_holder, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.userName.text = currentItem.userName
        holder.userId.text = currentItem.id.toString()

        /**1*/
        // როცა დავაჭირეთ ითემს შემოდის და ატანს ამ ინფორმაციას ლისტენერს
        holder.item.setOnClickListener {
            listenerUpdate?.goToUpdateFragment(currentItem)
        }

    }

    fun setData(userName: List<UserName>) {
        this.userList = userName
        notifyDataSetChanged()
    }

    override fun getItemCount() = userList.size

    fun initUpdateInterface(listener: IGoToUpdateFragment) {
        this.listenerUpdate = listener
    }

    private var listenerUpdate: IGoToUpdateFragment? = null
    interface IGoToUpdateFragment {
        fun goToUpdateFragment(userName: UserName)
    }

}