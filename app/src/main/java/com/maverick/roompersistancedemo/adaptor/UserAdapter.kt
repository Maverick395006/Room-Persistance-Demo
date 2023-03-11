package com.maverick.roompersistancedemo.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maverick.roompersistancedemo.data.model.User
import com.maverick.roompersistancedemo.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList = mutableListOf<User>()

    interface EventListener {
        fun onItemClick(position: Int, item: User)
    }

    private lateinit var mEventListener: EventListener

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemUserBinding.inflate(
            inflater,
            parent,
            false
        )
        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]
        try {
            holder.itemBinding.apply {
                tvUserId.text = currentItem.id.toString()
                tvUserFirstName.text = currentItem.firstName
                tvUserLastName.text = currentItem.lastName
                tvUserAge.text = currentItem.age.toString()
                root.setOnClickListener {
                    mEventListener.onItemClick(position, currentItem)
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = userList.size

    fun addAllUsers(mData: List<User>?) {
        userList.clear()
        userList.addAll(mData!!)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(internal var itemBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}