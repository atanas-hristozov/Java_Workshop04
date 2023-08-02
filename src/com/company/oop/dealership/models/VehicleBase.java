package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.FormattingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;


abstract public class VehicleBase implements Vehicle {
    public final static String COMMENTS_HEADER = "--COMMENTS--";
    public final static String NO_COMMENTS_HEADER = "--NO COMMENTS--";
    public static final int MAKE_NAME_LEN_MIN = 2;
    public static final int MAKE_NAME_LEN_MAX = 15;
    public static final String MAKE_NAME_LEN_ERR = format(
            "Make must be between %s and %s characters long!",
            MAKE_NAME_LEN_MIN,
            MAKE_NAME_LEN_MAX);
    public static final int MODEL_NAME_LEN_MIN = 1;
    public static final int MODEL_NAME_LEN_MAX = 15;
    public static final String MODEL_NAME_LEN_ERR = format(
            "Model must be between %s and %s characters long!",
            MODEL_NAME_LEN_MIN,
            MODEL_NAME_LEN_MAX);
    public static final double PRICE_VAL_MIN = 0;
    public static final double PRICE_VAL_MAX = 1000000;
    public static final String PRICE_VAL_ERR = format(
            "Price must be between %.1f and %.1f!",
            PRICE_VAL_MIN,
            PRICE_VAL_MAX);
    private final VehicleType VEHICLE_TYPE;

    private String make;
    private String Model;
    private int wheelsCount;
    private double price;
    private final List<Comment> comments;


    public VehicleBase(String make, String model, int wheelsCount, double price, VehicleType vehicleType) {
        setModel(model);
        setMake(make);
        setPrice(price);
        VEHICLE_TYPE = vehicleType;
        comments = new ArrayList<>();
        setWheelsCount(wheelsCount);
    }

    private void setMake(String make) {
        ValidationHelpers.validateIntRange(make.length(), MAKE_NAME_LEN_MIN, MAKE_NAME_LEN_MAX, MAKE_NAME_LEN_ERR);
        this.make = make;
    }

    private void setModel(String model) {
        ValidationHelpers.validateIntRange(model.length(), MODEL_NAME_LEN_MIN, MODEL_NAME_LEN_MAX, MODEL_NAME_LEN_ERR);
        Model = model;
    }

    private void setWheelsCount(int wheelsCount) {
        this.wheelsCount = wheelsCount;
    }

    private void setPrice(double price) {
        ValidationHelpers.validateDecimalRange(price, PRICE_VAL_MIN, PRICE_VAL_MAX, PRICE_VAL_ERR);
        this.price = price;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return Model;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public VehicleType getType() {
        return VEHICLE_TYPE;
    }

    @Override
    public int getWheels() {
        return wheelsCount;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    protected String printComments() {
        StringBuilder strBuilder = new StringBuilder();

        if (getComments().size() > 0) {
            strBuilder.append(COMMENTS_HEADER + "\n");
            for (Comment comment : getComments()) {
                strBuilder.append(comment.print());
            }
            strBuilder.append(COMMENTS_HEADER + "\n");
        } else {
            strBuilder.append(NO_COMMENTS_HEADER + "\n");
        }

        return strBuilder.toString();
    }

    @Override
    public String print() {
        return String.format(
                "%s:%n" +
                        "Make: %s%n" +
                        "Model: %s%n" +
                        "Wheels: %d%n" +
                        "Price: $%s%n", getType(), getMake(), getModel(), getWheels(), FormattingHelpers.removeTrailingZerosFromDouble(getPrice())
        );
    }
}
