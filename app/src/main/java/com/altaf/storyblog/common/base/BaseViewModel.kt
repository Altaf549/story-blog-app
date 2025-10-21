package com.altaf.storyblog.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * BaseViewModel that handles two types of LiveData:
 * 1. dataItem - for state/data updates
 * 2. eventItem - for one-time events like toast, navigation, etc.
 */
open class BaseViewModel<Data, Event> : ViewModel() {

    // Data type LiveData
    protected val _dataItem = MutableLiveData<Data>()
    val dataItem: LiveData<Data> = _dataItem

    // Event type LiveData (one-time events)
    protected val _eventItem = MutableLiveData<Event>()
    val eventItem: LiveData<Event> = _eventItem


}
