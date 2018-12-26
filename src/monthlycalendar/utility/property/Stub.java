package monthlycalendar.utility.property;


class Stub extends Property {
    Stub(String propName) {
        propName_ = propName;
    }

    @Override
    public String getProperty(String key) throws PropertyNotFoundException {
        throw new PropertyNotFoundException(propName_, key);
    }


    //
    private final String propName_;
}
