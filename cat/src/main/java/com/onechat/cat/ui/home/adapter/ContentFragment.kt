package com.onechat.cat.ui.home.adapter

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.benjamin.base.BaseFragment
import com.benjamin.utils.eighteen.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.onechat.cat.R
import com.onechat.cat.entity.AccountEntity
import kotlinx.android.synthetic.main.content_fragment.*

class ContentFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.content_fragment
    }

    override fun initView() {
        super.initView()
        val id = arguments?.getInt(KEY_ID)
        ToastUtils.showShort("id:$id")
        rv.layoutManager = LinearLayoutManager(context)
        val adapter = object :BaseQuickAdapter<List<AccountEntity>,BaseViewHolder>(R.layout.adapter_article){

            override fun convert(helper: BaseViewHolder?, item: List<AccountEntity>?) {
            }

        }
        rv.adapter = adapter
    }

    companion object {
        private const val KEY_ID = "key_id"
        fun newInstance(id: Int) = ContentFragment().apply { arguments = Bundle().apply { putInt(
            KEY_ID, id) } }
    }

    /*private lateinit var viewModel: ContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

}
