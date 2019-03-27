package com.kuopin.frame.ui.test.view

import com.chad.library.adapter.base.entity.SectionEntity
import com.kuopin.frame.entity.TestEntity

class MySection : SectionEntity<TestEntity> {
    constructor(isHeader: Boolean, header: String) : super(isHeader, header) {}

    constructor(testEntity: TestEntity) : super(testEntity) {

    }
}