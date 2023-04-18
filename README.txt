# spring-data-ldap & openldap 연동

## 트러블 슈팅
1. User 신규 생성 시 @Id 참조 못하는 현상 경험
- id 를 LdapNameBuilder를 통해 신규 생성하였더니 Repo에서 update 로직을 타서 NameFound 예외 발생
- 내부 구현 로직 확인 후 @DnAttribute 속성 처리 필요 확인
- 기존 uid 필드를 @Attribute 에서 @DnAttribute 로 설정 후 정상 등록 확인
