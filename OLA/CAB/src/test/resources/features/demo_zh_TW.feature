# language: zh-TW
功能: 系統基本功能測試
  測試系統功能
  場景: 管理員查看所有人員資訊
    假設系統人員已完成登錄
    當系統人員點選查詢所有人員資訊
    那麼顯示人員數應與所有人員數相等

  場景: 登錄人員查看自已資料
    假設用戶"user1"已完成登錄
    當"user1"點選我的個人資料
    那麼顯示"user1"的個資