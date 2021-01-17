package com.yrc.aisservice.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DataSeg {
    private String mmsi;
    private Date baseDateTime;
    private Float sog;
    private Float cog;
    private Float heading;
    private String vesselName;
    private String imo;
    private String callSign;
    private Integer vesselType;
    private String status;
    private Float length;
    private Float width;
    private Float draft;
    private Float cargo;
    private Float lat;
    private Float lon;
}
