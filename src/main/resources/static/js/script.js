const now = new Date();
const formattedDate = now.toISOString().slice(0, 16);
document.getElementById("datetimePicker").setAttribute("min", formattedDate);

document.getElementById("datetimePicker").addEventListener("input", function() {
    const selectedDate = new Date(this.value);
    const dayOfWeek = selectedDate.getDay();


    if (dayOfWeek === 6) {
        alert("Saturdays are not available for appointments.");
        this.value = "";
    } else {
        let minTime = "09:00";
        let maxTime = "22:00";

        if (dayOfWeek === 0) {
            minTime = "09:00";
            maxTime = "16:00";
        }

        const currentTime = selectedDate.getHours() * 100 + selectedDate.getMinutes();
        const minTimeCheck = minTime.replace(":", "") * 1;
        const maxTimeCheck = maxTime.replace(":", "") * 1;


        if (currentTime < minTimeCheck || currentTime > maxTimeCheck) {
            alert(`Appointments are only allowed between ${minTime} and ${maxTime}.`);
            this.value = "";
        }

        if (bookedDates.includes(selectedDate)) {
            alert("This date is already booked. Please choose another date.");
            this.value = "";
        }
    }
});


function confirmDelete() { return confirm("Are you sure you want to delete this sale?"); }