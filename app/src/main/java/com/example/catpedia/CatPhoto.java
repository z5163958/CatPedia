package com.example.catpedia;
import java.util.List;

public class CatPhoto {

        @PrimaryKey
        private String id;

        private String url;

        public List<Breeds> getBreeds() {
            return breeds;
        }

        public void setBreeds(List<Breeds> breeds) {
            this.breeds = breeds;
        }

        private List<Breeds> breeds;


        public String getId() {
            return id;
        }

        public  String getUrl() {
            return url;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setUrl(String url) {
            this.url = url;
        }


    }
