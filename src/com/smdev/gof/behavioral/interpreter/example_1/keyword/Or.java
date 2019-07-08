package com.smdev.gof.behavioral.interpreter.example_1.keyword;

import com.smdev.gof.behavioral.interpreter.example_1.Context;
import com.smdev.gof.behavioral.interpreter.example_1.AbstractExpressionNonTerminal;
import com.smdev.gof.behavioral.interpreter.example_1.Expression;
import com.smdev.gof.behavioral.interpreter.example_1.Dog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Or extends AbstractExpressionNonTerminal {

    public Or(Context context) {
        super(context);
    }

    @Override
    public void parse() {
        parseChildren();
    }

    @Override
    public List<Dog> evaluate(List<Dog> unfiltered) throws Exception {
        Set<Dog> result = new HashSet<>(unfiltered);

        List<Expression> children = getChildren();
        for (Expression ch : children) {
            result.addAll(ch.evaluate(unfiltered)); // keep only common elements
        }
        return new ArrayList<>(result);
    }

}
