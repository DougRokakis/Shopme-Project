	$(document).ready(function() {
		//WITH THIS FUNCTION IN PLACE, WHEN THE CANCEL BUTTON IS CLICKED THE WEBSITE WILL BE REDIRECTED TO /users
		$("#buttonCancel").on("click", function(){
			window.location = moduleURL;
		});
		//FUNCTION TO CHECK FOR SIZE OF PHOTO FILE. IF TOO GREAT REPORTS ERROR, IF SIZE APPROPRIATE 
		//UPLOADS PHOTO WITH AID OF showImageThumbnail FUNCTION
		$("#fileImage").change(function(){
			if (!checkFileSize(this)) {
			return;
		}

		showImageThumbnail(this);
		});
	});
	//FUNCTION FOR UPLOADING OF THUMBNAIL PHOTO
	function showImageThumbnail(fileInput){
		var file = fileInput.files[0];
		var reader = new FileReader();
		reader.onload = function(e){
			$("#thumbnail").attr("src", e.target.result);
		};
		
		reader.readAsDataURL(file);
	}
	
	function checkFileSize(fileInput) {
	fileSize = fileInput.files[0].size;

	if (fileSize > MAX_FILE_SIZE) {
		fileInput.setCustomValidity("You must choose an image less than " + MAX_FILE_SIZE + " bytes!");
		fileInput.reportValidity();

		return false;
	} else {
		fileInput.setCustomValidity("");

		return true;
	}	
}

	function showModalDialog(title, message) {
		$("#modalTitle").text(title);
		$("#modalBody").text(message);
		$("#modalDialog").modal();
	}
	
	function showErrorModal(message) {
		showModalDialog("Error", message);
	}
	
	function showWarningModal(message) {
		showModalDialog("Warning", message);
	}