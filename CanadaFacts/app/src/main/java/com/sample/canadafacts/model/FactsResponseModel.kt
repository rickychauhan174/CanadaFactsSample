package com.sample.canadafacts.model

class FactsResponseModel {
    var title: String? = null
    var rows: ArrayList<Row>? = null

    class Row {
        val title: String? = null
        val description: String? = null
        val imageHref: String? = null
    }
}
