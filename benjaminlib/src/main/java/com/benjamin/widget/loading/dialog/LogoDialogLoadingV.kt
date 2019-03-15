package com.benjamin.widget.loading.dialog

import android.content.Context
import com.benjamin.R
import com.benjamin.widget.loading.LoadingV
import com.benjamin.widget.loading.progress.LogoProgressView

class LogoDialogLoadingV @JvmOverloads constructor(
        context: Context,
        loadingV: LoadingV = DialogLoadingV(LogoProgressView(context), R.style.LoadingDialogUnTran)
) : LoadingV by loadingV