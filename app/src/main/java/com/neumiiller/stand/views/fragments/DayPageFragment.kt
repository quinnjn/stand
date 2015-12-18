package com.neumiiller.stand.views.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.neumiiller.stand.R
import com.neumiiller.stand.db.StandDB
import com.neumiiller.stand.models.Content
import com.neumiiller.stand.models.Day

/**
 * Created by qneumiiller on 12/18/15.
 */
class DayPageFragment(private var day: Day?) : Fragment() {

    var dayContent: TextView? = null
    var dayContentEdit: EditText? = null
    var clicked = false
    var standDb: StandDB? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        standDb = StandDB(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewGroup = inflater?.inflate(R.layout.fragment_daypage, container, false)
        dayContent = viewGroup?.findViewById(R.id.day_content) as TextView
        dayContentEdit = viewGroup?.findViewById(R.id.day_content_edit) as EditText
        dayContent?.text = day?.content?.raw
        dayContentEdit?.setText(day?.content?.raw)
        return viewGroup
    }

    override fun onPause() {
        super.onPause()
        var newContent = dayContentEdit?.text.toString()
        if (!newContent.equals(day?.content?.raw)) {
            day = Day(day?.time!!, Content(newContent))
            standDb?.addDay(day!!)
        }
    }
}