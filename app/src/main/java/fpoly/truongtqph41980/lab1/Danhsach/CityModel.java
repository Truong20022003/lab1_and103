package fpoly.truongtqph41980.lab1.Danhsach;

public class CityModel {

        private String name;
        private String country;
        private String foundedYear;

        public CityModel() {
        }

        public CityModel(String name, String country, String foundedYear) {
            this.name = name;
            this.country = country;
            this.foundedYear = foundedYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getFoundedYear() {
            return foundedYear;
        }

        public void setFoundedYear(String foundedYear) {
            this.foundedYear = foundedYear;
        }
    }

