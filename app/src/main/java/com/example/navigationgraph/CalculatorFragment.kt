package com.example.navigationgraph


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_calculator.*

/**
 * A simple [Fragment] subclass.
 */
class CalculatorFragment : Fragment() {
    var lastNumber: Float = 0.0f
    var lastOperator: Int = 0
    var canModify: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goto_converter.setOnClickListener {
            val direction = CalculatorFragmentDirections.actionCalculatorFragmentToConverterFragment2(lastNumber)
            Navigation.findNavController(view).navigate(direction)
        }

        val clearOperationIfNeeded = {
            if (textview_calc_result.text.isNotEmpty() && !textview_calc_result.text[0].isDigit()) textview_calc_result.text = ""
        }

        val addToCurrentText = { value: String ->
            if (canModify) textview_calc_result.text = textview_calc_result.text.toString().plus(value)
        }

        val saveLastNumber = {
            if (textview_calc_result.text.isNotEmpty() && textview_calc_result.text[0].isDigit()) lastNumber = textview_calc_result.text.toString().toFloat()
        }

        val clearAll = {
            lastNumber = 0.0f
            lastOperator = 0
            canModify = true
            textview_calc_result.text = ""
        }

        button0.setOnClickListener {
            clearOperationIfNeeded()
            addToCurrentText("0")
        }
        button1.setOnClickListener {
            clearOperationIfNeeded()
            addToCurrentText("1")
        }
        button2.setOnClickListener {
            clearOperationIfNeeded()
            addToCurrentText("2")
        }
        button3.setOnClickListener {
            clearOperationIfNeeded()
            addToCurrentText("3")
        }
        button4.setOnClickListener {
            clearOperationIfNeeded()
            addToCurrentText("4")
        }
        button5.setOnClickListener {
            clearOperationIfNeeded()
            addToCurrentText("5")
        }
        button6.setOnClickListener {
            clearOperationIfNeeded()
            addToCurrentText("6")
        }
        button7.setOnClickListener {
            clearOperationIfNeeded()
            addToCurrentText("7")
        }
        button8.setOnClickListener {
            clearOperationIfNeeded()
            addToCurrentText("8")
        }
        button9.setOnClickListener {
            clearOperationIfNeeded()
            addToCurrentText("9")
        }
        buttondot.setOnClickListener {
            if (textview_calc_result.text.isNotEmpty() && textview_calc_result.text.isDigitsOnly()) addToCurrentText(".")
        }

        buttonadd.setOnClickListener {
            canModify = true
            saveLastNumber()
            lastOperator = 1
            textview_calc_result.text = "+"
        }
        buttonmin.setOnClickListener {
            canModify = true
            saveLastNumber()
            lastOperator = 2
            textview_calc_result.text = "-"
        }
        buttonmult.setOnClickListener {
            canModify = true
            saveLastNumber()
            lastOperator = 3
            textview_calc_result.text = "*"
        }
        buttondiv.setOnClickListener {
            canModify = true
            saveLastNumber()
            lastOperator = 4
            textview_calc_result.text = "/"
        }

        buttonequals.setOnClickListener {
            if (lastOperator != 0 && textview_calc_result.text[0].isDigit()) {
                canModify = false
                val currentNumber = textview_calc_result.text.toString().toFloat()
                val result = when (lastOperator) {
                    1 -> lastNumber + currentNumber
                    2 -> lastNumber - currentNumber
                    3 -> lastNumber * currentNumber
                    4 -> lastNumber / currentNumber
                    else -> 0f
                }
                lastNumber = result
                textview_calc_result.text = result.toString()
            }
        }

        buttonrem.setOnClickListener {
            if (canModify && textview_calc_result.text.isNotEmpty() && textview_calc_result.text[0].isDigit()) {
                textview_calc_result.text = textview_calc_result.text.substring(0, textview_calc_result.text.length - 1)
            }
        }
        buttonrem.setOnLongClickListener {
            clearAll()
            true
        }
    }


}
