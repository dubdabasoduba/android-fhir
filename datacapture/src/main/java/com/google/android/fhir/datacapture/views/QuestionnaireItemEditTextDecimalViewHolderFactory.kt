/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.fhir.datacapture.views

import android.text.InputType
import com.google.fhir.r4.core.Decimal
import com.google.fhir.r4.core.Extension
import com.google.fhir.r4.core.QuestionnaireResponse

object QuestionnaireItemEditTextDecimalViewHolderFactory :
    QuestionnaireItemEditTextViewHolderFactory() {
    override fun getQuestionnaireItemViewHolderDelegate() =
        object : QuestionnaireItemEditTextViewHolderDelegate(
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED,
            isSingleLine = true
        ) {
            override fun getValue(text: String): QuestionnaireResponse.Item.Answer.Builder? {
                return text.toDoubleOrNull()?.let {
                    QuestionnaireResponse.Item.Answer.newBuilder()
                        .apply {
                            value = QuestionnaireResponse.Item.Answer.ValueX.newBuilder()
                                .setDecimal(Decimal.newBuilder().setValue(it.toString()).build())
                                .build()
                        }
                }
            }

            override fun getText(answer: QuestionnaireResponse.Item.Answer.Builder?): String {
                return answer?.value?.decimal?.value?.toString() ?: ""
            }

            override fun validateMaxValue(extension: Extension, inputValue: String): Boolean {
                println("decimal max value")
                TODO("Not yet implemented")
            }

            override fun validateMinValue(extension: Extension, inputValue: String): Boolean {
                TODO("Not yet implemented")
            }
        }
}
