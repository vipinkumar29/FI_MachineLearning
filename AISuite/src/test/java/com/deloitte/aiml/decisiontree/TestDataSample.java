package com.deloitte.aiml.decisiontree;

import java.util.Optional;

import com.deloitte.aiml.decisiontree.data.DataSample;
import com.deloitte.aiml.decisiontree.label.Label;

public class TestDataSample implements DataSample {
    
    private Object value;
    
    private Label label;
    
    public TestDataSample(Object value, Label label) {
        super();
        this.value = value;
        this.label = label;
    }

    @Override
    public Optional<Object> getValue(String column) {
        return Optional.of(value);
    }

    @Override
    public Label getLabel() {
        return label;
    }

}
