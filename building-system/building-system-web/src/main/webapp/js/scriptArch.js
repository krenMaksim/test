window.onload = function(){
	putLocalStorage();
	showCorrections();
	
	//document.getElementById("date").innerHTML = "";
	selectSmeta();
    selectSmena();
    selectedUser();
    
	getTodayDate();
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

// слушатель на переход во вкладку редактирование и тянем с собой номер записи в журнале
function redactionRecord(id){
    var magazineId = document.getElementsByName("magazineId")[0];
    magazineId.value = id;
    document.getElementById("redactor").submit();
}

// настройка связанного списка при выборе наименования работ
function selectSmeta(){
    var smeta = document.getElementsByName("addSmeta")[0];
    var selectedNumber = smeta.options.selectedIndex;
    
    var addEdIzm = document.getElementsByName("addEdIzm")[0];
    addEdIzm.options[selectedNumber].selected = true;
    
    var ostatok = document.getElementsByName("ostatok")[0];
    ostatok.options[selectedNumber].selected = true;
    
    var pp = document.getElementsByName("ppSmeta")[0];
    pp.options[selectedNumber].selected = true;
    
    var textarea = document.getElementById("smetaText");
    textarea.innerHTML = smeta.value;
    
    var rest = document.getElementById("rest");
    rest.innerHTML ="*остаток: "+ ostatok.value;
    
    var edIzm = document.getElementById("edIzm");
    edIzm.innerHTML = addEdIzm.value;
}

// установка списка согласно указанной смене
function selectSmena(){
    var selectSmena = document.getElementById("selectSmena");
    var smena = document.getElementsByName("addSmena")[0];
    
    for(var i = 0; i < smena.length; i++) {            
        if(smena[i].value == selectSmena){
            smena.options[i].selected = true;
            break;
        }            
    }
}

// настройка связанного списка юзеров
function selectedUser(){
    var user = document.getElementsByName("addUser")[0];
    var selectedNumber = user.options.selectedIndex;
    
    var role = document.getElementsByName("role")[0];
    role.options[selectedNumber].selected = true;
    
    var userRole = document.getElementById("userRole");
    userRole.innerHTML = role.value;
}

// установка скриптом текущей даты
function getTodayDate(){
	
	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth()+1;
	if(month<10){
		month = "0"+month;
	}
	var dat = today.getDate();
    if(dat<10){
		dat = "0"+dat;
	}
	var dateFormat = year+"-"+month+"-"+dat;
	
	var date = document.getElementById("addDate");
	date.innerHTML = dateFormat;
	
	document.getElementsByName("addDate")[0].value = dateFormat;
}