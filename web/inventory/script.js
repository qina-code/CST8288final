/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
// Function to handle form submission for adding a new item
$('#inventoryForm').submit(function(event) {
    event.preventDefault();

    var itemName = $('#itemName').val();
    var quantity = $('#quantity').val();
    var expirationDate = $('#expirationDate').val();

    // Send AJAX request to add the new item
   $.ajax({
    url: '/FWRP/inventory/add_item',
    method: 'POST',
    data: {
        itemName: itemName,
        quantity: quantity,
        expirationDate: expirationDate
    },
    success: function(response) {
        console.log('Success: Item added successfully!');
        console.log('Response:', response);
        // Refresh the surplus item list
        populateListedItems();
    },
    error: function(xhr, status, error) {
        console.error('Error adding item:', error);
        console.log('XHR:', xhr);
        console.log('Status:', status);
        // Handle error
        alert('Error adding item. Please try again.');
    }
});

});

// Function to populate the surplus items dropdown
function populateListedItems() {
    $.ajax({
        url: '/FWRP/getSurplusItems', // Update this URL with your actual backend endpoint
        method: 'GET',
        success: function(response) {
            // Clear existing options
            $('#surplusItem').empty();
            $('#listedItem').empty();

            // Add each surplus item to the dropdown
            response.forEach(function(item) {
                $('#surplusItem').append($('<option>', {
                    value: item.name,
                    text: item.name
                }));
                $('#listedItem').append($('<option>', {
                    value: item.name,
                    text: item.name
                }));
            });
        },
        error: function(xhr, status, error) {
            console.error('Error fetching surplus items:', error);
            // Handle error
        }
    });
}

// Call the function to populate surplus items when the page loads
$(document).ready(function() {
    populateListedItems();
});

