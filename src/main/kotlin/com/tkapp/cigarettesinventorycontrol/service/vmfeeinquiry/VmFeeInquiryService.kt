package com.tkapp.cigarettesinventorycontrol.service.vmfeeinquiry

import com.tkapp.cigarettesinventorycontrol.controller.vmfeeinquiry.VmFeeInquiryOut
import com.tkapp.cigarettesinventorycontrol.controller.vmfeeinquiry.VmFeeInquirySelectForm

interface VmFeeInquiryService {
    fun select(form: VmFeeInquirySelectForm): VmFeeInquiryOut
}