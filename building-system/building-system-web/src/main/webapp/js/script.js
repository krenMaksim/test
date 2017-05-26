window.onload = function(){
	putLocalStorage();
	showCorrections();
}
// отображаем исправления в журнале
function showCorrections(){	
	var checkBox = document.getElementById("corrections");
	var corrections = document.getElementsByClassName("correction");
	for (var i = 0; i < corrections.length; i++) {
		if(checkBox.checked){ 
			corrections[i].style.display = "";
		}else{
			corrections[i].style.display = "none";
			
		}
	}
}

// сохраняем состояние выбранных чекбоксов и даты после отправки запроса на сервер
function putLocalStorage(){
    var dataWith = document.querySelector('input[name="date_with"]');
    dataWith.value = localStorage.getItem('date_with');
    
    var dateOn = document.querySelector('input[name="date_on"]');
    dateOn.value = localStorage.getItem('date_on');
    
    document.getElementById('corrections').onblur = function() {
        if(document.getElementById('corrections').checked) {
            localStorage.setItem('corrections', "true");
        } else {
            localStorage.setItem('corrections', "false");
        }
    }
    if (localStorage.getItem('corrections') == "true") {
        document.getElementById("corrections").setAttribute('checked','checked');
    }
    
    var surnames = document.getElementsByName("surname_initials");
    for (var i = 0; i < surnames.length; i++) {
        surnames[i].onchange = function() {
            if(this.checked) {
                localStorage.setItem(this.value, "true");                           
            } else {
                localStorage.setItem(this.value, "false");                           
            }
        }
    }

    for (var i = 0; i < surnames.length; i++) {
        if (localStorage.getItem(surnames[i].value) == "true") {
            surnames[i].setAttribute('checked','checked');                        
        }
    }
}

window.onunload = function () {
    localStorage.clear();
};

//сохраняем данные в  localStorage если запрос пошел на сервер
function saveStorage(){
    window.onunload = function(){}; 
}