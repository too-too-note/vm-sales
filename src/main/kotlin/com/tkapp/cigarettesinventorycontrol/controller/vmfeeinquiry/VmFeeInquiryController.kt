package com.tkapp.cigarettesinventorycontrol.controller.vmfeeinquiry

import com.tkapp.cigarettesinventorycontrol.service.vmfeeinquiry.VmFeeInquiryService
import com.tkapp.cigarettesinventorycontrol.service.vmfeeinquiry.VmFeeInquiryServiceImpl
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("vm-fee-inquiry")
class VmFeeInquiryController(
        private val vmFeeInquiryService: VmFeeInquiryService
) {

    @PostMapping("/select")
    fun selectItem(@RequestBody form: VmFeeInquirySelectForm): VmFeeInquiryOut {
        return vmFeeInquiryService.select(form)
    }
}