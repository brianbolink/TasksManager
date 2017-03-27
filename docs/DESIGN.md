##�ճ̹���ϵͳ

----
###��̬��� : 

####���������
1. **���� (����)**
2. **��ֹʱ��**
3. **�����˭ (������)**
4. **˭����������**
5. **�Ƿ��ظ� , ����ظ� ? ����**
6. **�Ƿ����� ? ������� ? ���� ? �����ʼ�**
7. **��Ҫ�� (��Ϊ������Ҫ�Եȼ�)**
8. **�Ƿ���� (��Ϊ�����ȼ�)**
9. ��ע
10. ������
11. ����
12. ��ǩ
13. ��������

####��Ŀ�����
1. ����

####�û������
1. **�û���**
2. **����**
3. �����Ŷ� (���)

####�Ŷ�
1. �Ŷ���
2. �ŶӶӳ�
2. �Ŷ���ҳ
3. �Ŷӳ�Ա

----
####���ݿ���� : 

####���� : 
```
CREATE DATABASE `tasksmanager` /*!40100 DEFAULT CHARACTER SET utf8 */;
```

#####�û��� : 

1. ID (����)
2. �û���
3. ����
4. �Ŷ�ID (���)
```
CREATE TABLE `users` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `sex` tinyint(2) DEFAULT NULL,
  `birthday` YEAR DEFAULT 1970,
  `email` varchar(64) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `registeTime` int(16) DEFAULT NULL,
  `salt` varchar(8) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `tokenFailTime` int(16) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

#####�����
1. ID (����)
2. ����
3. ����
4. ������ID
6. ��Ҫ�� (0 ����Ҫ , 1, 2, 3 ��Ҫ)
7. ������ (0 , 1, 2, 3)
8. �Ƿ������ (״̬)
```
CREATE TABLE `tasks` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `taskname` varchar(16) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `creatorID` int(16) NOT NULL,
  `importance` tinyint DEFAULT 0,
  `emergency` tinyint DEFAULT 0,
  `status` tinyint DEFAULT 0,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

#####����\_������\_������
1. ID (����)
2. ����ID
3. ������ID
```
CREATE TABLE `executors_tasks_relationship` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `executorID` int(16) NOT NULL,
  `taskID` int(16) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

#####��ǩ��̬�� (ֻ��¼��ǩ������)
1. ID (����)
2. ����
```
CREATE TABLE `lables` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
#####��ǩ���������
1. ID (����)
2. ����ID
3. ��ǩ ID
```
CREATE TABLE `lables_tasks_relationship` (
  `ID` int(16) NOT NULL AUTO_INCREMENT,
  `taskID` int(16) NOT NULL,
  `lableID` int(16) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```
��Ŀ��
�Ŷӱ�
1. �Ŷ� ID (����)
2. �Ŷ�����
2. ������ ID
3. �ӳ� ID
4. ����

�û��Ŷӹ�����
1. ID (����)
2. �û� ID
3. �Ŷ� ID

���ۻỰ�� (һ�����ۻỰ������)
1. ID (����)

�������ݱ�
1. ����ID
2. ��������
3. ������ID
4. ʱ��
5. ���������� ID

---
###��̬���

####�������ģ��
1. **�������**
2. **ɾ������**
3. **�޸�����(�������)(�������)**
4. **��ѯ����**

####�û�����ģ��
1. **�û���½**
2. **�û�ע��**
3. �û��һ�����

���û�����
1. ���û�֮�以�ཻ��
2. �Ŷ�Э��

�û�����֧��ģ�� : 
1. �ʼ����ͷ��� (�������� / �û�ע�� / �һ�����)
2. ���ŷ��ͷ��� (�������� / �û�ע�� / �һ�����)
3. 

�����淶 : 
1. �շ�����
2. ���� ID �����ַ�ȫ����д

---
###��С�����Բ�Ʒ
��һ�׶� : ֻ�ṩ�������ɾ�Ĳ� , �û���½ע��
�ڶ��׶� : �ṩһ��������б�ִ��˳�� (���ս����� / ��Ҫ�Ե�)
�����׶� : �ṩ���۹���
���Ľ׶� : �����Ŷ� , �����Ŷӹ���
����׶� : �һ����빦��
...

---
���� sql ���ģ����� : 
1. ע��
```
INSERT INTO `users` (username, password, sex, birthday, email, phone, registeTime, salt, token, tokenFailTime) VALUES ('WangYihang', '123456', '1', '1997', 'wangyihanger@gmail.com', '15776624248', '1490591867', '', '', '1490791867');
```
2. ��½
```
select * from users where username = 'WangYihang';
�ж�������ܺ��У���Ƿ�ͨ��
```
3. ��ѯĳ���û����������� tasks
```
select * from tasks where creatorID = '1';
```
4. ���ݱ�ǩ��ѯ���û����������� tasks
```
select ID from lables where name like '%keyword%';
select taskID from lables_tasks_relationship where lableID = 'ID'
select * from tasks where ID = 'taskID' and creator = 'WangYihang'
```
4. ��ѯ�����û� (����Ա����)
```
select * from users where username like '%keyword%'
```
