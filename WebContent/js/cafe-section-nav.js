window.onload=()=>{
	var cafeInfoBtn=document.getElementById("cafeInfoBtn");
	var selfInfoBtn=document.getElementById("selfInfoBtn");
	var sectionNavHeaderContentCafe=document.getElementById("section-nav-header-content-cafe");
	var sectionNavHeaderContentSelf=document.getElementById("section-nav-header-content-self");
	cafeInfoBtn.onclick=(e)=>{
		sectionNavHeaderContentCafe.style.display="block";
		sectionNavHeaderContentSelf.style.display="none";
		cafeInfoBtn.style.color="black";
		selfInfoBtn.style.color="gray";
	}
	selfInfoBtn.onclick=(e)=>{
		sectionNavHeaderContentSelf.style.display="block";
		sectionNavHeaderContentCafe.style.display="none";
		selfInfoBtn.style.color="black";
		cafeInfoBtn.style.color="gray";
	}
}