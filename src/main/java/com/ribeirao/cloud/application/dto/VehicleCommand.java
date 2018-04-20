package com.ribeirao.cloud.application.dto;

public class VehicleCommand {
    private Integer id;
    private String name;
    private String desc;
    private VehicleTypeCommand type;
    private String plate;

    private VehicleCommand() {}

    public VehicleCommand(Integer id, String name, String desc, VehicleTypeCommand type,
            String plate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.type = type;
        this.plate = plate;
    }

    public VehicleCommand(String name, String desc, VehicleTypeCommand type, String plate) {
        this(null, name, desc, type, plate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public VehicleTypeCommand getType() {
        return type;
    }

    public void setType(VehicleTypeCommand type) {
        this.type = type;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "VehicleCommand{" + "id=" + id + ", name='" + name + '\'' + ", desc='" + desc + '\''
                + ", type=" + type + ", plate='" + plate + '\'' + '}';
    }
}
