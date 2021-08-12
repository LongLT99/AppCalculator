package com.example.appcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.appcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var numberButtons = listOf<Button>()
    private var actionButtons = listOf<Button>()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = binding.root
        setContentView(view)
        initViews()
    }

    override fun onClick(v: View?) = with(binding) {
        when (v) {
            buttonZero -> appendNumber( getString(R.string.text_0))
            buttonOne -> appendNumber(getString(R.string.text_1))
            buttonTwo -> appendNumber(getString(R.string.text_2))
            buttonThree -> appendNumber(getString(R.string.text_3))
            buttonFour -> appendNumber(getString(R.string.text_4))
            buttonFive -> appendNumber(getString(R.string.text_5))
            buttonSix -> appendNumber(getString(R.string.text_6))
            buttonSeven -> appendNumber(getString(R.string.text_7))
            buttonEight -> appendNumber(getString(R.string.text_8))
            buttonNine -> appendNumber(getString(R.string.text_9))
            buttonDot -> appendNumber(getString(R.string.text_dot))
            buttonPlus -> appendAction(getString(R.string.text_add))
            buttonMinus -> appendAction(getString(R.string.text_minus))
            buttonMul -> appendAction(getString(R.string.text_mul))
            buttonDivide -> appendAction(getString(R.string.text_div))
            buttonClear -> cleanNumber()
            buttonEquals -> showResult()
        }
    }

    private fun initViews() {
        with(binding) {
            numberButtons = listOf(
                buttonZero,
                buttonOne,
                buttonTwo,
                buttonThree,
                buttonFour,
                buttonFive,
                buttonSix,
                buttonSeven,
                buttonEight,
                buttonNine,
                buttonDot
            )
            actionButtons = listOf(buttonPlus, buttonMinus, buttonMul, buttonDivide)
            buttonClear.setOnClickListener(this@MainActivity)
            buttonEquals.setOnClickListener(this@MainActivity)
        }
        numberButtons.forEach { it.setOnClickListener(this) }
        actionButtons.forEach { it.setOnClickListener(this) }
    }

    private fun calculate(a: Double, b: Double, action: String) = with(binding) {
        when (action) {
            getString(R.string.text_add) -> textExpression.append((a + b).toString())
            getString(R.string.text_minus) -> textExpression.append((a - b).toString())
            getString(R.string.text_mul) -> textExpression.append((a * b).toString())
            getString(R.string.text_div) -> textExpression.append((a / b).toString())
            getString(R.string.text_equal) -> textExpression.append((a).toString())
        }
    }

    private fun appendNumber(string: String) {
        with(binding) {
            if (textAction.text.isNotEmpty()) {
                if (textAction.text.toString() != getString(R.string.text_equal)) {
                    textResult.append(string)
                } else {
                    textAction.text = getString(R.string.text_emp)
                    textExpression.text = string
                }
            } else {
                textExpression.append(string)
            }
        }
    }

    private fun appendAction(string: String) {
        with(binding) {
            if (textExpression.text.isNotEmpty()) {
                if (textAction.text.isNotEmpty()) {
                    val a = textExpression.text.toString().toDouble()
                    val act = textAction.text.toString()
                    textExpression.text = getString(R.string.text_emp)
                    if (textAction.text.toString().equals(getString(R.string.text_equal))) {
                        val b = 0.0
                        calculate(a, b, act)
                    } else {
                        val b = textResult.text.toString().toDouble()
                        textResult.text = getString(R.string.text_emp)
                        calculate(a, b, act)
                    }
                }
                textAction.text = string
            }
        }
    }

    private fun cleanNumber() {
        with(binding) {
            textExpression.text = getString(R.string.text_emp)
            textResult.text = getString(R.string.text_emp)
            textAction.text = getString(R.string.text_emp)
        }
    }

    private fun showResult() {
        with(binding) {
            if (textAction.text.isNotEmpty() && textResult.text.isNotEmpty()
                && textExpression.text.isNotEmpty()
            ) {
                val a = textExpression.text.toString().toDouble()
                val b = textResult.text.toString().toDouble()
                val act = textAction.text.toString()
                textExpression.text = getString(R.string.text_emp)
                textResult.text = getString(R.string.text_emp)
                textAction.text = getString(R.string.text_equal)
                calculate(a, b, act)
            }
        }
    }

}
