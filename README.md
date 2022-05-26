# 포트폴리오

## Description
웹 프로젝트 구현 ‘영화 구매 대여 서비스’

# Tech Stack
- Back-end : Java, Javascript, 
- Front-end : CSS, JavaScript
- Develop Tool - Spring boot
- DB - MySQL
- 배포 - AWS

# 프로젝트 영화 링크 사이트 입니다. 
http://movieprojectnotflex.com:8383/main


## ERD 구조
<img width="685" alt="image" src="https://user-images.githubusercontent.com/97656198/170171818-671bf1f1-8e3a-4098-b4b7-055bc69fec62.png">



## 


## 사용자화면

![화면 캡처 2022-05-25 121819](https://user-images.githubusercontent.com/97656198/170172644-5673674f-130c-45de-bcfb-7cefa46b390b.png)

![화면 캡처 2022-05-26 144807](https://user-images.githubusercontent.com/97656198/170426394-fd6112ee-a985-4e5a-bfdd-c86a69cfa1d0.png)


timeleaf에  조건문을 사용하여 사용자가 미로그인시 로그인시 그리고 관리자 일때 다르게 표시했습니다. 

## 영화 검색기능 
![화면 캡처 2022-05-25 131434](https://user-images.githubusercontent.com/97656198/170179224-115a2386-d88b-4805-a4b8-866b778c9d71.png)
![화면 캡처 2022-05-26 144831](https://user-images.githubusercontent.com/97656198/170426649-1f9c9dbf-7c68-49c2-8a96-0078c6c7871e.png)
삼항연산자를 사용해서 조건에 맞으면 selected 속성을 갖도록 구현 했습니다. 
![화면 캡처 2022-05-26 145353](https://user-images.githubusercontent.com/97656198/170426619-a5dae79f-c67a-4daf-9496-93e300ce3718.png)

 model 객체를 사용해서 PageVO를 view 화면으로 가져오는 기능을 구현하였습니다. 


## 채팅기능 구현
![화면 캡처 2022-05-25 131401](https://user-images.githubusercontent.com/97656198/170179045-7adf6c36-b6e9-445d-9642-4e5a48363532.png)

하단 Footer 부분에 모든 유저가 참여할수 있는 채팅 api  기능을 추가하였습니다.

## 영화 결제 기능 페이지 

![화면 캡처 2022-05-25 131455](https://user-images.githubusercontent.com/97656198/170179343-ee61df7d-528c-4740-9a8f-6513e93814e0.png)
![화면 캡처 2022-05-26 151627](https://user-images.githubusercontent.com/97656198/170428683-c29ccab0-b43a-469c-b696-53f0a8dd6dad.png)

콜백함수를 이용해서 결과값을 불러오도록 구현했습니다. 
결제 시스템은 아임포트를 이용하여 카카오 api 페이기능을 구현했습니다.
## 관리자 페이지 

![화면 캡처 2022-05-25 131613](https://user-images.githubusercontent.com/97656198/170179501-984f35cf-f8a2-4218-8d2e-6ebae7b5bdec.png)
![화면 캡처 2022-05-26 151830](https://user-images.githubusercontent.com/97656198/170429015-b9721f9e-7bb5-4bdc-ae63-28845788ca83.png)

![화면 캡처 2022-05-26 151850](https://user-images.githubusercontent.com/97656198/170429431-0e45f657-8b95-4da5-90c8-90ed5ba89fae.png)
![화면 캡처 2022-05-26 152036](https://user-images.githubusercontent.com/97656198/170429438-0db108e9-f677-4350-987d-2c8494de4ee9.png)

컨트롤러에 조건문으로 달마다 가입자수를 반영 시켰습니다.


## 관리자페이지 문의사항

![화면 캡처 2022-05-25 131641](https://user-images.githubusercontent.com/97656198/170179723-baaa58ea-7917-4638-b0a5-6e389f1a8466.png)

문의사항은 이페이지가 아닌 회원이 등록한 페이지에서 값을 가지고 나온것입니다. 검색기능 또한 회원이 등록한 검색한 기능을 가지고 나온것입니다. 

## 관리자페이지 공지사항

![화면 캡처 2022-05-25 131653](https://user-images.githubusercontent.com/97656198/170180075-58a0ef39-4a31-43dd-8925-f4cdbf03f6ef.png)

첨부파일이 여기에서는 핵심입니다. 
이미지뿐만 아니라 모든 파일을 올릴수 있고 다운 받을수 있도록 구현해놨습니다. 
## 관리자 페이지 : 회원관리 페이지 
![화면 캡처 2022-05-25 131731](https://user-images.githubusercontent.com/97656198/170180239-7324e335-22ea-4e07-813b-d475ebd0593e.png)

회원가입된 유저를 볼수있는 화면입니다. ID를 클릭시 유저의 대여 영상 목록과 구매 영상 목록을 볼수있습니다. 


