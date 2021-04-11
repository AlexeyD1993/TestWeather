package Temperature;

public class TemperatureConverter {
    public String get_currTemperature() {
        return _currTemperature;
    }

    private String _currTemperature;

    public String get_changedTemperature() {
        return _changedTemperature;
    }

    private String _changedTemperature;

    private void convert() {
        if (_currTemperature.contains("°C")) {
            _changedTemperature = String.valueOf(Math.round((Float.parseFloat(
                    _currTemperature.replace("°C", "")) * 9/5.0) +32)) +
                    "°F";
        }
        else if (_currTemperature.contains("°F")) {
            _changedTemperature = String.valueOf(Math.round((Float.parseFloat(
                    _currTemperature.replace("°F", "")) - 32) * 5.0/9)) +
                    "°C";
        }
        else _changedTemperature = "";
    }

    public TemperatureConverter(String temp) {
        this._currTemperature = temp;
        convert();
    }
}
