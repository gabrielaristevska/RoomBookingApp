package mk.finki.ukim.emt.lab.model.dto;

import mk.finki.ukim.emt.lab.model.Category;

public class AccommodationDto {
    private String name;
    private Category category;
    private Long hostId;
    private Integer numRooms;

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Long getHostId() {
        return hostId;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }
}
